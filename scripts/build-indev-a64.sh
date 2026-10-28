#!/bin/bash
set -e
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PZROOT="$ROOT/pz-switch"

echo "=== Generating reachability metadata for Minecraft Indev ==="
mkdir -p "$ROOT/config-gen"
python3 - << 'EOF'
import json, os, sys, zipfile

jar_path = "minecraft_indev_clean.jar"
if not os.path.exists(jar_path):
    print("JAR not found:", jar_path)
    sys.exit(1)

with zipfile.ZipFile(jar_path) as z:
    names = sorted({n[:-6].replace('/', '.') for n in z.namelist()
                    if n.endswith('.class') and not n.startswith('META-INF/') and n != 'module-info.class'})

refl = [{'type': n, 'allDeclaredConstructors': True, 'allDeclaredFields': True, 'allDeclaredMethods': True} for n in names]
os.makedirs("config-gen", exist_ok=True)
res = [{'glob': '**'}]
json.dump({'reflection': refl, 'resources': res}, open("config-gen/reachability-metadata.json", "w"), indent=2)
print(f"Generated reachability config for {len(refl)} classes")
EOF

echo "=== Compiling Indev to ARM64 relocatable native object with GraalVM ==="
mkdir -p "$ROOT/build-a64" && cd "$ROOT/build-a64"
rm -rf tmp mc.o

CP="$ROOT/minecraft_indev_clean.jar"
if [ -d "$ROOT/lib" ]; then
  for j in $(find "$ROOT/lib" -name '*.jar' 2>/dev/null); do
    CP="$CP:$j"
  done
fi

native-image -cp "$CP" \
  -H:ConfigurationFileDirectories="$ROOT/config-gen" --no-fallback -J-Xmx16g \
  --enable-native-access=ALL-UNNAMED --enable-url-protocols=http,https -H:+ReportExceptionStackTraces -H:+AddAllCharsets \
  --target=linux-aarch64 -march=compatibility -H:+UnlockExperimentalVMOptions -H:-ForeignAPISupport \
  -H:-MremapImageHeap -H:-SpawnIsolates \
  -H:CCompilerPath=/usr/bin/aarch64-linux-gnu-gcc -H:+ExitAfterRelocatableImageWrite -H:TempDirectory="$PWD/tmp" \
  -o mc net.minecraft.client.Minecraft

cp tmp/SVM-*/mc.o mc.o
echo "=== AArch64 Object Built Successfully: $(ls -lh mc.o) ==="
