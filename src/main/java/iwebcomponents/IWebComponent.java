package iwebcomponents;

import org.openqa.selenium.By;

public interface IWebComponent<T extends IWebComponent>{

    public T withLocator(By byLocator);

    public T untilPresent();

    public T untilClickable();

    public T untilVisible();

    public T untilInVisible();
}
