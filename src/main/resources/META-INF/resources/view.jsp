<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL name="/login/verify-otp" var="verifyotp">
</liferay-portlet:actionURL>

<div class="portlet-body">
    <style>
        .modal-dialog {
            position: initial !important;
            /*width: 40% !important;*/
        }
        
        .modal-backdrop {
            background-color: white !important;
            opacity: 1.0 !important;
        }
    </style>
    <div class="modal fade in" id="myModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Validar código de autenticación</h4> </div>
                <div class="modal-body">
                    <div class="col-sm-12">
                        <div class="col-sm-12">
                            <div class="col-sm-12">
                                <div align="center" style="width: 94%" id="verifySuccessFailMsgDiv"></div>
                                <div align="center" style="width: 94%" id="verifyFailMsgDiv"></div>
                            </div>
                            <div class="row">
                                <!-- div class="col-sm-3"></div-->
                                <div class="/*col-sm-6*/">
                                    <form action="${ verifyotp }" class="form " id="_TwoFactorAuthVerify_two-fa-otp-form" method="post" name="_TwoFactorAuthVerify_two-fa-otp-form">
                                        <input class="field form-control" id="_TwoFactorAuthVerify_formDate" name="_TwoFactorAuthVerify_formDate" type="hidden" value="1579633204823">

                                        <div class="form-group input-text-wrapper" style="text-align: center;">
                                        	<c:if test='${ !validado }'>
                                            <label class="control-label" for="_TwoFactorAuthVerify_otpCode" style="font-weight: bold; font-size: 20px;">
                                                Código
                                            </label>
                                            <input class="field form-control custom-text-otp" id="_TwoFactorAuthVerify_otpCode" name="_TwoFactorAuthVerify_otpCode" type="text" value="">
                                        	</c:if>
                                        	<c:if test='${ validado }'>
                                        		<label class="control-label" for="_TwoFactorAuthVerify_otpCode" style="font-weight: bold; font-size: 20px;">
                                                	Usted ya ha verificado anteriormente
                                            	</label>
                                        	</c:if>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                            	<c:if test='${ !validado }'>
                                                <button class="btn btn btn-primary btn-block btn-primary btn-default" id="_TwoFactorAuthVerify_ok" name="_TwoFactorAuthVerify_ok" type="submit">
                                                    <span class="lfr-btn-label">Verificar</span>
                                                </button>
                                                </c:if>
                                                <c:if test='${ validado }'>
                                                	<a class="btn btn btn-primary btn-block btn-default" href="/group/portal-agentes" id="_TwoFactorAuthVerify_logout">
                                                    <span class="lfr-btn-label">Continuar</span>
                                                </a>
                                                </c:if>
                                            </div>
                                            <div class="col-sm-6">

                                                <a class="btn btn btn-primary btn-block btn-default" href="/c/portal/logout" id="_TwoFactorAuthVerify_logout">
                                                    <span class="lfr-btn-label">Cerrar sesión</span>
                                                </a>

                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div align="right" id="back" style="display: none;"><a onclick="back()" style="cursor: pointer;">Back</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal-backdrop fade in"></div>

</div>

<style>
	.custom-text-otp {
		text-align: center;
	    font-size: xx-large;
	    font-weight: bold;
	    color: lightseagreen;
	    letter-spacing: 10px;
	}
</style>