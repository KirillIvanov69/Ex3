import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SecondTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/kir50/Desktop/JavaAppiumAutomation2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента. Предлагается назвать ее assertElementHasText.
     * На вход эта функция должна принимать локатор элемент, ожидаемый текст и текст ошибки,
     * который будет написан в случае, если элемент по этому локатору не содержит текст, который мы ожидаем.
     * <p>
     * Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит текст
     * (в разных версиях приложения это могут быть тексты "Search..." или "Search Wikipedia", правильным вариантом следует
     * считать тот, которые есть сейчас). Очевидно, что тест должен использовать написанный ранее метод.
     * <p>
     * Результат выполнения задания нужно отдельным коммитом положить в git.
     * В качестве ответа прислать ссылку на коммит. Если вам потребовалось несколько коммитов для
     * выполнения одного задания - присылайте ссылки на все эти коммиты с комментариями.
     */
    @Test
    public void testAssertElementHasText() {
        By locator = By.xpath("//*[contains(@text, 'Поиск по Википедии')]");
        String errorMessage = "Cannot find element with text 'Поиск по Википедии'";
        String expectedCorrectText = "Поиск по Википедии";

        assertElementHasText(locator, expectedCorrectText, errorMessage);
    }

    private void assertElementHasText(By by, String expectedText, String errorTextMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorTextMessage + "/n");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        String actualText = element.getAttribute("text");

        Assert.assertEquals(expectedText, actualText);
    }


}
