#!/bin/bash
set -e
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PZROOT="$ROOT/pz-switch"
DEPS="$PZROOT/deps"
HORIZON="$PZROOT/horizon"

mkdir -p "$DEPS" && cd "$DEPS"
MUSLGCC="$DEPS/musl-aarch64/bin/musl-gcc"

echo "=== Building musl 1.2.5 for aarch64 ==="
if [ ! -f musl-1.2.5.tar.gz ]; then
  curl -fLO https://musl.libc.org/releases/musl-1.2.5.tar.gz
fi

if [ ! -x "$MUSLGCC" ]; then
  rm -rf musl-1.2.5 && tar xf musl-1.2.5.tar.gz
  (cd musl-1.2.5 && ./configure --target=aarch64 CROSS_COMPILE=aarch64-linux-gnu- --prefix="$DEPS/musl-aarch64" --disable-shared \
     && make -j$(nproc) && make install)
  aarch64-linux-gnu-ar rc "$DEPS/musl-aarch64/lib/libatomic_asneeded.a" || true
fi

echo "=== Building musl-nx (patched for Horizon OS) ==="
if [ ! -f "$DEPS/musl-nx-out/lib/libc.a" ]; then
  rm -rf musl-nx && mkdir musl-nx && tar xf musl-1.2.5.tar.gz -C musl-nx --strip-components=1
  patch -d musl-nx -p1 < "$HORIZON/musl-nx.patch"
  (cd musl-nx && CFLAGS="-fPIE -O2" ./configure --target=aarch64 CROSS_COMPILE=aarch64-linux-gnu- --prefix="$DEPS/musl-nx-out" \
     --disable-shared && make -j$(nproc) && make install)
fi

echo "=== Building zlib ==="
if [ ! -f "$DEPS/musl-nx-out/lib/libz.a" ]; then
  if [ ! -f zlib-1.3.1.tar.gz ]; then
    curl -fLO https://zlib.net/fossils/zlib-1.3.1.tar.gz
  fi
  rm -rf zlib-1.3.1 && tar xf zlib-1.3.1.tar.gz
  (cd zlib-1.3.1 && CC="$MUSLGCC" CFLAGS="-O2 -fPIE" ./configure --static --prefix="$DEPS/musl-nx-out" && make -j$(nproc) libz.a && make install)
fi

echo "=== Building libffi ==="
if [ ! -f "$DEPS/libffi-3.5.2/aarch64-unknown-linux-gnu/.libs/libffi.a" ]; then
  if [ ! -f libffi-3.5.2.tar.gz ]; then
    curl -fLO https://github.com/libffi/libffi/releases/download/v3.5.2/libffi-3.5.2.tar.gz
  fi
  rm -rf libffi-3.5.2 && tar xf libffi-3.5.2.tar.gz
  (cd libffi-3.5.2 && CC="$MUSLGCC" CFLAGS="-O2 -fPIE" ./configure --enable-builddir=aarch64-unknown-linux-gnu --host=aarch64-linux-gnu \
     --disable-shared --enable-static --disable-exec-static-tramp --disable-docs && make -j$(nproc))
fi

echo "=== Switch base dependencies ready ==="
