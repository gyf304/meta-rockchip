DESCRIPTION = "Rockchip binary loader"

LICENSE = "BINARY"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"
NO_GENERIC_LICENSE[BINARY] = "LICENSE.TXT"

DEPENDS = "rk-binary-native"

SRC_URI = " \
	git://github.com/rockchip-linux/rkbin.git;branch=master \
	https://raw.githubusercontent.com/rockchip-linux/rkbin/yocto-old/LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9;name=license \
"

SRC_URI[license.md5sum] = "564e729dd65db6f65f911ce0cd340cf9"

SRCREV = "323ec3b55f84836ba105e131365c4da27ad68f44"
S = "${WORKDIR}/git"

MINILOADER_rk3328 ?= "bin/rk33/rk3328_miniloader_*.bin"
MINILOADER_rk3399 ?= "bin/rk33/rk3399_miniloader_v*.bin"

DDR_rk3328 ?= "bin/rk33/rk3328_ddr_786MHz_*.bin"
DDR_rk3399 ?= "bin/rk33/rk3399_ddr_800MHz_*.bin"

inherit deploy

DDR_BIN = "ddr.bin"
LOADER_BIN = "loader.bin"
MINILOADER_BIN = "miniloader.bin"
ATF_BIN = "atf.bin"
UBOOT_IMG = "uboot.img"
TRUST_IMG = "trust.img"

RKBINARY_DEPLOY_DIR = "${DEPLOYDIR}/rk-binary"

do_deploy () {
	install -d ${RKBINARY_DEPLOY_DIR}
	[ ${DDR} ] && cp ${S}/${DDR} ${RKBINARY_DEPLOY_DIR}/${DDR_BIN}
	[ ${MINILOADER} ] && cp ${S}/${MINILOADER} ${RKBINARY_DEPLOY_DIR}/${MINILOADER_BIN}
	[ ${LOADER} ] && cp ${S}/${LOADER} ${RKBINARY_DEPLOY_DIR}/${LOADER_BIN}
	[ ${ATF} ] && cp ${S}/${ATF} ${RKBINARY_DEPLOY_DIR}/${ATF_BIN}

	:
}

addtask deploy before do_build after do_compile

do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_tar[noexec] = "1"
