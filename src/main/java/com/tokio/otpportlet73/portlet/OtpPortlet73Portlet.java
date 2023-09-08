package com.tokio.otpportlet73.portlet;

import com.tokio.otpportlet73.constants.OtpPortlet73PortletKeys;

import java.io.IOException;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;

/**
 * @author urielfloresvaldovinos
 */
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=OtpPortlet",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.name=" + OtpPortlet73PortletKeys.OTP,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
			"com.liferay.portlet.requires-namespaced-parameters=false", 
			"add-process-action-general-error-action=false"
		},
		service = Portlet.class
	)

public class OtpPortlet73Portlet extends MVCPortlet {
	
	@Override
	public void doView( RenderRequest renderRequest, RenderResponse renderResponse) 
			throws IOException, PortletException {
		
        HttpServletRequest httpreq = PortalUtil.getHttpServletRequest(renderRequest);

		final HttpSession session = httpreq.getSession();
		
		Boolean status = (Boolean)session.getAttribute("IS_NOTIFIED_OTP_LANDING");
		System.out.println(status);
		if (Validator.isNull(status) || !status) {
			session.setAttribute("IS_NOTIFIED_OTP_LANDING", true);
		}
		
		Boolean isVerified = (Boolean) session.getAttribute("IS_VERIFIED");
		System.out.println(isVerified);
		if (Validator.isNotNull(isVerified) && isVerified)
			renderRequest.setAttribute("validado", true);
		
		super.doView(renderRequest, renderResponse);
	}
}