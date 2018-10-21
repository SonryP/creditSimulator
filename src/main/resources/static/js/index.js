

document.addEventListener("DOMContentLoaded", function() {
  
    // Init selectize and datepicker
    $('[data-toggle="datepicker"]').datepicker();
    $('#select-months').selectize();

    // Variables of ranges
    var sliderAmount = $('#sliderAmount');
    var sliderFees = $('#sliderFees');

    // Span of amount of ranges
    var amount = $('#simulatorAmount');
    var fees = $('#simulatorFees');
    
    // Inputs
    var rut = $('#rutInput');
    var salary = $('#salaryInput')
    var date = $('#datePickerInput');
    var selectmonths = $('#select-months');

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
                url: "/validaRut",
                data: {"rut": datos},
                cache: false,
                timeout: 600000,
                success: function (data) {
                    data= $.parseJSON(data);
                    if (data.codigo === "OK"){
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
                url: "/validaSalario",
                data: {"salario": datos},
                cache: false,
                timeout: 600000,
                success: function (data) {
                    data= $.parseJSON(data);
                    if (data.codigo === "OK"){
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

    // Replace text of span from range
    sliderAmount.change(function() {
        console.log(sliderAmount.val());
        amount.text( `\$${sliderAmount.val()}`);
    });
    
    sliderFees.change(function() {
        console.log(sliderFees.val());
        console.log(fees.html())
        fees.text(`Cuotas: ${sliderFees.val()}`);
    });



    btnSubmit.click(function(){
        var validate = true;

        if (salary.val() === '' || rut.val() === '' || amount.val() === '' || fees.val() === '' || date.val() === '') {
            validate = false;
        }

        if (validate) {
            alert('DALE');
        } else {
            alert("NEGATIVO");
        }
    });


});
