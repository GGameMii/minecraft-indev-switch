#!/bin/bash
set -e
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PZROOT="$ROOT/pz-switch"
HORIZON="$PZROOT/horizon/app"

echo "=== Prelinking Java / SubstrateVM object ==="
export PZROOT="$ROOT"
export GRAALVM="${GRAALVM:-$JAVA_HOME}"

bash "$PZROOT/prelink-j.sh" "$ROOT/build-a64/mc.o" "$ROOT/build-a64/j.o"

echo "=== Linking Horizon OS NRO Application ==="
cd "$HORIZON"
make clean || true

make -j$(nproc) NAME=minecraft_indev TITLE="Minecraft Indev MP" BUILD=build-indev GL=1 \
  J_OBJ="$ROOT/build-a64/j.o" DEFINES='-DLX_ROOT=\"sdmc:/switch/minecraft_indev\"'

if [ -f "$HORIZON/minecraft_indev.nro" ]; then
  cp "$HORIZON/minecraft_indev.nro" "$ROOT/minecraft_indev.nro"
  echo "=========================================================="
  echo "SUCCESS! Created $ROOT/minecraft_indev.nro"
  ls -lh "$ROOT/minecraft_indev.nro"
  echo "=========================================================="
else
  echo "Error: NRO not generated!"
  exit 1
fi
