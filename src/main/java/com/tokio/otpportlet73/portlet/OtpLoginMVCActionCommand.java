package com.tokio.otpportlet73.portlet;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.tokio.otp.model.TwoFactor;
import com.tokio.otp.service.TwoFactorLocalService;
import com.tokio.otpportlet73.constants.OtpPortlet73PortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		 property = {
				 "javax.portlet.name="+ OtpPortlet73PortletKeys.OTP,
				 "mvc.command.name=/login/verify-otp",
				 },
		 service = MVCActionCommand.class
		 )
public class OtpLoginMVCActionCommand extends BaseMVCActionCommand {
	
	@Reference
	private TwoFactorLocalService _TwoFactorLocalService;

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("VERIFY CODE");
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);
			
		HttpServletRequest request = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(actionRequest));
		
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		final HttpSession session = request.getSession();
		
		User user = (User) request.getAttribute(WebKeys.USER);
		TwoFactor code = null;
		try {
			code = _TwoFactorLocalService.getTokenByUsuario(user.getUserId(), user.getCompanyId(), user.getGroupId());
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
		if (Validator.isNotNull(code)) {
			String codeInput = ParamUtil.getString(actionRequest, "_TwoFactorAuthVerify_otpCode");
					
			System.out.println(code.getVerificationData());
			System.out.println(codeInput);
			System.out.println(code.getVerificationData() == codeInput);
			
			String privateGroup = themeDisplay.getPathFriendlyURLPrivateGroup();
			String fiendUrl = themeDisplay.getSiteGroup().getFriendlyURL();
			
			System.out.println(privateGroup+fiendUrl);
			if (code.getVerificationData().equals(codeInput)) {
				session.setAttribute("IS_VERIFIED", true);
				System.out.println("CORRECT");
				//actionResponse.sendRedirect(privateGroup+fiendUrl);
				actionResponse.sendRedirect("/group/portal-agentes/");
			}
		}
	}

}
