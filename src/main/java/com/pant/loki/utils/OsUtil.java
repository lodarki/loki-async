package com.pant.loki.utils;

import com.pant.loki.entity.EPlatform;

public class OsUtil {

	private static String OS = System.getProperty("os.name").toLowerCase();

	private static EPlatform platform;

	public static boolean isLinux() {
		return OS.contains("linux");
	}

	public static boolean isMacOS() {
		return OS.contains("mac") && OS.indexOf("os") > 0 && !OS.contains("x");
	}

	public static boolean isMacOSX() {
		return OS.contains("mac") && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
	}

	public static boolean isWindows() {
		return OS.contains("windows");
	}

	public static boolean isOS2() {
		return OS.contains("os/2");
	}

	public static boolean isSolaris() {
		return OS.contains("solaris");
	}

	public static boolean isSunOS() {
		return OS.contains("sunos");
	}

	public static boolean isMPEiX() {
		return OS.contains("mpe/ix");
	}

	public static boolean isHPUX() {
		return OS.contains("hp-ux");
	}

	public static boolean isAix() {
		return OS.contains("aix");
	}

	public static boolean isOS390() {
		return OS.contains("os/390");
	}

	public static boolean isFreeBSD() {
		return OS.contains("freebsd");
	}

	public static boolean isIrix() {
		return OS.contains("irix");
	}

	public static boolean isDigitalUnix() {
		return OS.contains("digital") && OS.indexOf("unix") > 0;
	}

	public static boolean isNetWare() {
		return OS.contains("netware");
	}

	public static boolean isOSF1() {
		return OS.contains("osf1");
	}

	public static boolean isOpenVMS() {
		return OS.contains("openvms");
	}

	/**
	 * 获取操作系统名字
	 * @return 操作系统名
	 */
	public static EPlatform getOSname(){

		if (platform == null) {
			if(isAix()){
				platform = EPlatform.AIX;
			}else if (isLinux()) {
				platform = EPlatform.Linux;
			}else if (isWindows()) {
				platform = EPlatform.Windows;
			}else if (isDigitalUnix()) {
				platform = EPlatform.Digital_Unix;
			}else if (isFreeBSD()) {
				platform = EPlatform.FreeBSD;
			}else if (isHPUX()) {
				platform = EPlatform.HP_UX;
			}else if (isIrix()) {
				platform = EPlatform.Irix;
			}else if (isMacOS()) {
				platform = EPlatform.Mac_OS;
			}else if (isMacOSX()) {
				platform = EPlatform.Mac_OS_X;
			}else if (isMPEiX()) {
				platform = EPlatform.MPEiX;
			}else if (isNetWare()) {
				platform = EPlatform.NetWare_411;
			}else if (isOpenVMS()) {
				platform = EPlatform.OpenVMS;
			}else if (isOS2()) {
				platform = EPlatform.OS2;
			}else if (isOS390()) {
				platform = EPlatform.OS390;
			}else if (isOSF1()) {
				platform = EPlatform.OSF1;
			}else if (isSolaris()) {
				platform = EPlatform.Solaris;
			}else if (isSunOS()) {
				platform = EPlatform.SunOS;
			}else{
				platform = EPlatform.Others;
			}
		}

		return platform;
	}
}
