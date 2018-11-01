# Copyright (C) 2016 - 2017 Jacob Chen <jacob2.chen@rock-chips.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Rockchip firmware"
DESCRIPTION = "Rockchip firmware such as for the WIFI, BT"

LICENSE = "proprietary-binary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://github.com/rockchip-linux/rk-rootfs-build.git \
	https://raw.githubusercontent.com/rockchip-linux/rkbin/yocto-old/LICENSE.TXT;name=license \
"
SRC_URI[license.md5sum] = "564e729dd65db6f65f911ce0cd340cf9"

S = "${WORKDIR}/git/overlay-firmware"

DEFAULT_ROCKCHIP_CAM_IQ_XML ?= ""

inherit allarch

do_install () {
	install -d ${D}/system/etc/firmware/
	cp -rf ${S}/system/etc/firmware/* ${D}/system/etc/firmware/

	install -d ${D}/etc/firmware/
	cp -rf ${S}/etc/firmware/*.hcd ${D}/etc/firmware/

	install -d ${D}/etc/cam_iq/
	cp -rf ${S}/etc/cam_iq/* ${D}/etc/cam_iq/

	[ -z "${DEFAULT_ROCKCHIP_CAM_IQ_XML}" ] && \
	[ -f "${D}/etc/cam_iq/${DEFAULT_ROCKCHIP_CAM_IQ_XML}" ] && \
	ln -s "/etc/cam_iq/${DEFAULT_ROCKCHIP_CAM_IQ_XML}" ${D}/etc/cam_iq.xml

	:
}

PACKAGES =+ "${PN}-wifi \
	${PN}-bt \
	${PN}-camera \
"

FILES_${PN}-wifi = "/system/etc/* /etc/firmware/*"
FILES_${PN}-bt = "/system/etc/* /etc/firmware/*"
FILES_${PN}-camera = "/etc/cam_iq/* /etc/cam_iq.xml"
