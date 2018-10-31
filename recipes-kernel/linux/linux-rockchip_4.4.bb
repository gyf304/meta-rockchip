# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "openssl-native"

SRC_URI = " \
	git://github.com/rockchip-linux/kernel.git;branch=release-4.4; \
"


SRCREV = "4311650fc8ae85d181313f08ab482e3dacc7bf4d"
LINUX_VERSION = "4.4.154"

SRCREV_tinker-rk3288 = "15dbb651be03ed7f9905f2e7c54b61d55011f54a"
LINUX_VERSION_tinker-rk3288 = "4.4.103"

# Override local version in order to use the one generated by linux build system
# And not "yocto-standard"
LINUX_VERSION_EXTENSION = ""
PR = "r1"
PV = "${LINUX_VERSION}"

# Include only supported boards for now
COMPATIBLE_MACHINE = "(rk3036|rk3066|rk3288|rk3328|rk3399)"
deltask kernel_configme
