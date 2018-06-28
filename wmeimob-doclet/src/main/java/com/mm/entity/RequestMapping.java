package com.mm.entity;

import com.mm.doclet.IApiConstant;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.FieldDoc;

import lombok.Data;

/**
 * 解析RequestMapping
 * @author zJun
 * @date 2018年6月27日 下午9:40:32
 */
@Data
public class RequestMapping {
	private String name;
	private String[] value;
	private String[] path;
	private String method;
	private String[] params;
	private String[] headers;
	private String[] consumes;
	private String[] produces;
	
	public static RequestMapping build(AnnotationDesc ad) {
		RequestMapping rm = new RequestMapping();
		
		switch (ad.annotationType().name()) {
		case IApiConstant.REQUEST_MAPPING:
			break;
		case IApiConstant.GET_MAPPING:
			rm.setMethod(IApiConstant.GET);
			break;
		case IApiConstant.POST_MAPPING:
			rm.setMethod(IApiConstant.POST);
			break;
		case IApiConstant.PUT_MAPPING:
			rm.setMethod(IApiConstant.PUT);
			break;
		case IApiConstant.DELETE_MAPPING:
			rm.setMethod(IApiConstant.DELETE);
			break;
		default:
			return null;
		}
		
		ElementValuePair[]  ev = ad.elementValues();
		for(ElementValuePair evp : ev) {
			switch (evp.element().name()) {
			case "name":
				rm.setName(evp.value().value().toString());
				break;
			case "value":
			case "path":
				AnnotationValue[] avs = (AnnotationValue[]) evp.value().value();
				rm.setName(avs[0].value().toString());
				break;
			case "headers":
				avs = (AnnotationValue[]) evp.value().value();
				String[] headers = new String[avs.length];
				for (int i = 0; i < avs.length; i++) {
					AnnotationValue av = avs[i];
					headers[i] = av.value().toString();
				}
				rm.setHeaders(headers);
				break;
			case "method":
				avs = (AnnotationValue[]) evp.value().value();
				rm.setMethod(((FieldDoc) avs[0].value()).name());
				break;
			default:
				break;
			}
		}
		if(rm.getName() == null) {
			rm.setName("/");
		}
		return rm;
	}
}
