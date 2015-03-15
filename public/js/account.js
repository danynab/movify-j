var paypalLinkClicked = false;
var cajasturLinkClicked = false;
var cajasturLoaded = false;

function generateCajasturPaymentData(months) {
    $.getJSON('/movifyj/generateCajasturPayment', {
        months: months
    }, function (cajastur) {
        $('#MerchantID').val(cajastur.commerce_data.MERCHANT_ID);
        $('#AcquirerBIN').val(cajastur.commerce_data.ACQUIRER_BIN);
        $('#TerminalID').val(cajastur.commerce_data.TERMINAL_ID);
        $('#Num_operacion').val(cajastur.operation);
        $('#Importe').val(cajastur.price);
        $('#TipoMoneda').val(cajastur.commerce_data.TIPO_MONEDA);
        $('#Exponente').val(cajastur.commerce_data.EXPONENTE);
        $('#URL_OK').val(cajastur.url_ok);
        $('#URL_NOK').val(cajastur.url_error);
        $('#Firma').val(cajastur.signature);
        $('#Descripcion').val(cajastur.description);
        cajasturLoaded = true;
        if (cajasturLinkClicked) {
            $('#form-cajastur').submit();
        }
    });
}

function generatePaypalPaymentData(months) {
    $('#paypal-link').attr('href', '#');
    $.getJSON('/movifyj/generatePaypalPayment', {
        months: months
    }, function (paypal) {
        $('#paypal-link').attr('href', paypal.url);
        if (paypalLinkClicked) {
            $(location).attr('href', paypal.url);
            $('#payment-modal').modal('hide');
        }
    });
}

$(function () {
    $('a.checkout').bind('click', function () {
        paypalLinkClicked = false;
        cajasturLinkClicked = false;
        cajasturLoaded = false;
        $('#payment-modal-loading').hide();
        $('#payment-modal-content').show();
        var months = this.getAttribute('data-months')
        generateCajasturPaymentData(months);
        generatePaypalPaymentData(months);
        return false;
    });

    $('#paypal-link').bind('click', function () {
        paypalLinkClicked = true;
        var href = $('#paypal-link').attr('href');
        if (href == '#') {
            $('#payment-modal-loading').show();
            $('#payment-modal-content').hide();
            return false;
        }
        return true;
    });

    $('#form-cajastur').submit(function (event) {
        cajasturLinkClicked = true;
        if (!cajasturLoaded) {
            $('#payment-modal-loading').show();
            $('#payment-modal-content').hide();
            event.preventDefault();
        }
    });
});