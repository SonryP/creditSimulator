

$(document).ready(function() {
  
    // Variables of ranges
    var sliderAmount = $('#sliderAmount');
    var sliderFees = $('#sliderFees');

    // Span of amount of ranges
    var amount = $('#simulatorAmount');
    var fees = $('#simulatorFees');

    // Variables to Check
    var amountValue = 100;
    var feesValue = 4;
    var dateSelected = new Date();
    
    // Inputs
    var rut = $('#rutInput');
    var salary = $('#salaryInput')
    var date = $('#datePickerInput');
    var selectmonths = $('#select-months');
    
    // Init multiple select 
    selectmonths.select2({
        placeholder: 'Seleccione mes...',
        maximumSelectionLength: 2,
        'language': 'es',
    });

    // Init datepicker
    date.flatpickr({
        dateFormat: "d-m-Y",
        minDate: new Date().fp_incr(21),
        maxDate: new Date().fp_incr(30),
        locale: es.Spanish,
        onChange: function(selectedDates, dateStr, instance){
            dateSelected = new Date(selectedDates[0]);
            selectmonths.prop('disabled', false);
        }
    });
    
    

    // Submit
    var btnSubmit = $('#submitSimulator');

    // Disable inputs
    sliderAmount.prop('disabled', true);
    sliderFees.prop('disabled', true);
    salary.prop('disabled', true);
    date.prop('disabled', true);
    selectmonths.prop('disabled', true);

    // Toggle disabled of inputs
    rut.change(function() {
        var datos;
        
        if (rut.value === '') {
            salary.prop("disabled", true);
        } else {
            datos = rut.val();
            $.ajax({
                type: "POST",
                url: "/rutValidate",
                data: {"rut": datos},
                cache: false,
                timeout: 600000,
                success: function (data) {
                    data= $.parseJSON(data);
                    if (data.code === "OK"){
                        rut.val(rutFormat(rut.val()));
                        salary.prop("disabled", false);
                    }else{
                        alert("El rut no es valido, ingrese nuevamente.");
                    }
                }
            });
        }
    });

    salary.change(function() {
        var datos;
        if (salary.value === '') {
            salary.prop('disabled', true);
            sliderAmount.prop('disabled', true);
            sliderFees.prop('disabled', true);
            date.prop('disabled', true);
            if (rut.value === '') {
                salary.prop('disabled', true);
            } else {
                salary.prop('disabled', false);
            }
        } else {
            datos = salary.val();
            $.ajax({
                type: "POST",
                url: "/rentValidate",
                data: {"rent": datos},
                cache: false,
                timeout: 600000,
                success: function (data) {
                    data= $.parseJSON(data);
                    if (data.code === "OK"){
                        salary.val(toMiles(salary.val()));
                        date.prop('disabled', false);
                        sliderFees.prop('disabled', false);
                        sliderAmount.prop('disabled', false);
                    }else{
                        alert("Salario ingresado no es valido, ingrese nuevamente.");
                    }
                }
            });
        }
    });

    // sliderAmount events
    sliderAmount.on('input', function(){
        amount.text(`$${toMiles($(this).val())}`);
    });

    sliderAmount.on('change', function(){
        amountValue = $(this).val();
        console.log(amountValue);
    });
    
    // sliderFees events 
    sliderFees.on('input', function(){
        fees.text(`Cuotas: ${$(this).val()}`);
    });

    sliderFees.on('change', function(){
        feesValue = $(this).val();
        console.log(feesValue);
    });
    
    // Add point to miles
    function toMiles(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }

    // Rut formatter 
    function rutFormat(rut) {
        var rutWO = rut.replace(/\.-/g, '');
        var dv = rutWO[(rutWO.length - 1)];
        var rut = rutWO.substring(0, (rutWO.length - 1));
        rut = toMiles(rut);
        return (rut + '-' + dv);
    }

    // toValidate
    btnSubmit.click(function(){
        var validate = true;

        if ((amountValue >= 100 && amountValue <= 100000000) && (feesValue >= 4 && feesValue <= 60)) {
            validate = true;
        } else {
            validate = false;
        }
        if (salary.val() === '' || rut.val() === '' || amount.val() === '' || fees.val() === '' || date.val() === '') {
            validate = false;
        }

        if (validate) {
            // TODO SUBMIT
        } else {
            // TODO NO SUBMIT
        }
    });


});
