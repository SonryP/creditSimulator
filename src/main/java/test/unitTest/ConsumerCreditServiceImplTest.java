package test.unitTest;

import com.example.simulator.entity.Response;
import com.example.simulator.service.ConsumerCreditService;
import com.example.simulator.service.ConsumerCreditServiceImpl;
import com.example.simulator.service.UtilsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerCreditServiceImplTest {

    @Mock
    private UtilsService utilsService;

    @InjectMocks
    private ConsumerCreditService consumerCreditService = new ConsumerCreditServiceImpl();

    @Test
    public void rutValidateTestOK(){
        String rut = "199194429";
        Response response = consumerCreditService.rutValidate(rut);
        Assert.assertEquals("OK", response.getCode());
    }

    @Test
    public void rutValidateTestNOK(){
        String rut = "184571098";
        Response response = consumerCreditService.rutValidate(rut);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void rentValidateTestOK(){
        String rent = "400000";
        when(utilsService.isNumeric(rent)).thenReturn(true);
        Response response = consumerCreditService.rentValidate(rent);
        Assert.assertEquals("OK", response.getCode());
    }

    @Test
    public void rentValidateTestOnlyNumNOK(){
        String rent = "Prueba";
        when(utilsService.isNumeric(rent)).thenReturn(false);
        Response response = consumerCreditService.rentValidate(rent);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void rentValidateTestMinNOK(){
        String rent = "40";
        when(utilsService.isNumeric(rent)).thenReturn(true);
        Response response = consumerCreditService.rentValidate(rent);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void creditValidateTestOK(){
        String credit = "100000";
        when(utilsService.isNumeric(credit)).thenReturn(true);
        Response response = consumerCreditService.creditValidate(credit);
        Assert.assertEquals("OK", response.getCode());
    }

    @Test
    public void creditValidateTestOnlyNumNOK(){
        String credit = "Prueba";
        when(utilsService.isNumeric(credit)).thenReturn(false);
        Response response = consumerCreditService.creditValidate(credit);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void creditValidateTestMinNOK(){
        String credit = "10";
        when(utilsService.isNumeric(credit)).thenReturn(true);
        Response response = consumerCreditService.creditValidate(credit);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void numOfMonthlyFeesValidateOK(){
        Integer monthlyFees = 5;
        Response response = consumerCreditService.numOfMonthlyFeesValidate(monthlyFees);
        Assert.assertEquals("OK", response.getCode());
    }

    @Test
    public void numOfMonthlyFeesValidateMinNOK(){
        Integer monthlyFees = 300;
        Response response = consumerCreditService.numOfMonthlyFeesValidate(monthlyFees);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void numOfMonthlyFeesValidateMaxNOK(){
        Integer monthlyFees = 1;
        Response response = consumerCreditService.numOfMonthlyFeesValidate(monthlyFees);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void nonPaymentMonthsValidateOK(){
        ArrayList<Integer> nonPaymentMonths = new ArrayList<>(Arrays.asList(3, 6));
        Response response = consumerCreditService.nonPaymentMonthsValidate(nonPaymentMonths);
        Assert.assertEquals("OK", response.getCode());
    }

    @Test
    public void nonPaymentMonthsValidateEmptyNOK(){
        ArrayList<Integer> nonPaymentMonths = null;
        Response response = consumerCreditService.nonPaymentMonthsValidate(nonPaymentMonths);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void nonPaymentMonthsValidateMaxNOK(){
        ArrayList<Integer> nonPaymentMonths = new ArrayList<>(Arrays.asList(3, 6, 9, 12));
        Response response = consumerCreditService.nonPaymentMonthsValidate(nonPaymentMonths);
        Assert.assertEquals("NOK", response.getCode());
    }

    @Test
    public void nonPaymentMonthsValidateConsecutiveNOK(){
        ArrayList<Integer> nonPaymentMonths = new ArrayList<>(Arrays.asList(1,2));
        Response response = consumerCreditService.nonPaymentMonthsValidate(nonPaymentMonths);
        Assert.assertEquals("NOK", response.getCode());
    }
}
