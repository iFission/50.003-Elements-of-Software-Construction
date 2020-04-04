from selenium import webdriver
import requests
import re


def check_title(hyper_link):
    print(f"checking link: {hyper_link}")
    try:
        raw_html = requests.get(hyper_link).text
        match = re.search('<title>(.*?)</title>', raw_html)
        title = match.group(1) if match else None
        print(title)
        if title is not None:
            return True
        else:
            return False
    except:
        return False


if __name__ == "__main__":
    options = webdriver.ChromeOptions()
    options.binary_location = "//Volumes/Apps/Applications/Chrome.app/Contents/MacOS/Google Chrome"
    options.headless = True
    chrome_driver_binary = "/usr/local/bin/chromedriver"
    driver = webdriver.Chrome(chrome_driver_binary, chrome_options=options)

    try:
        driver.get(
            'file:///Users/ALEX/Documents/Term%205/50.003%20Elements%20of%20Software%20Construction/Problem%20Set/ps3/index.html'
        )
        links = driver.find_elements_by_xpath("//a[@href]")
        count = 0
        for link in links:
            print(link.get_attribute("href"))
            print(link)
            count += check_title(link.get_attribute("href"))

        print(
            f'RESULT: {count}/{len(links)} {"links have titles"if count > 1 else "link has title"}'
        )
    except:
        pass
    finally:
        driver.close()