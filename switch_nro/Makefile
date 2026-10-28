CXX := aarch64-none-elf-g++
CC  := aarch64-none-elf-gcc
ELF2NRO := $(DEVKITPRO)/tools/bin/elf2nro
NACPTOOL := $(DEVKITPRO)/tools/bin/nacptool

ARCH := -march=armv8-a+crc+crypto -mtune=cortex-a57 -mtp=soft -fPIE

INCLUDES := -Iinclude -I$(DEVKITPRO)/libnx/include -I$(DEVKITPRO)/portlibs/switch/include
LIBPATHS := -L$(DEVKITPRO)/libnx/lib -L$(DEVKITPRO)/portlibs/switch/lib

CXXFLAGS := -O2 -g $(ARCH) -Wall -std=c++17 -fno-rtti -fexceptions $(INCLUDES) -D__SWITCH__
LDFLAGS := -specs=$(DEVKITPRO)/libnx/switch.specs $(ARCH) -g $(LIBPATHS)
LIBS := -lglad -lEGL -lglapi -ldrm_nouveau -lopenal -lSDL2 -lpng -lz -lnx

TARGET := minecraft_indev
OBJS := build/main.o

all: $(TARGET).nro

build:
	mkdir -p build

build/main.o: source/main.cpp | build
	$(CXX) $(CXXFLAGS) -c "$<" -o "$@"

$(TARGET).elf: $(OBJS)
	$(CXX) $(LDFLAGS) $^ $(LIBS) -o "$@"

$(TARGET).nacp:
	$(NACPTOOL) --create "Minecraft Indev MP" "Indev Port" "0.2.0" "$@"

$(TARGET).nro: $(TARGET).elf $(TARGET).nacp
	$(ELF2NRO) "$<" "$@" --nacp="$(TARGET).nacp" --romfsdir=romfs
	@echo "===================================="
	@echo "SUCCESSFULLY BUILT $(TARGET).nro !"
	@echo "===================================="

clean:
	rm -rf build $(TARGET).elf $(TARGET).nro $(TARGET).nacp
