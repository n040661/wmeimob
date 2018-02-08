package com.wmeimob.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmojiFilter {
	
	
	public static void main(String[] args) {
//		String str = "阿斯达😳💥";
//		String str2 = "1231231";
		String str3 = "😳💥asdad";
		
//		containsEmoji(str);
//		containsEmoji(str2);
		containsEmoji(str3);
		
//		System.out.println(filterEmoji(str));
//		System.out.println(filterEmoji(str2));
		System.out.println(filterEmoji(str3));
		
	}

    /**
     * 检测是否有emoji字符
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isEmpty(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (!isEmojiCharacter(codePoint)) {
            	LogUtil.debug(log, "发现emoji："+source);
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = new StringBuilder(source.length());

        int len = source.length();
        
        boolean flag = false;

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            } else {
            	flag = true;
            }
        }

        if (!flag) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else if(buf.length() == 0){
            	return "非法昵称";
            } else {
                return buf.toString();
            }
        }

    }
}
