from selenium import webdriver
from random import choice

if __name__ == "__main__":
    options = webdriver.ChromeOptions()
    options.binary_location = "//Volumes/Apps/Applications/Chrome.app/Contents/MacOS/Google Chrome"
    # options.headless = True
    chrome_driver_binary = "/usr/local/bin/chromedriver"
    driver = webdriver.Chrome(chrome_driver_binary, chrome_options=options)

    driver.get('https://istd.sutd.edu.sg/')
    links = driver.find_elements_by_xpath("//a[@href]")
    while (links):
        random_link = choice(links)
        print(random_link.get_attribute("href"))
        # random_link.click() # raises element not interactable
        driver.get(random_link.get_attribute("href"))
        links = driver.find_elements_by_xpath("//a[@href]")
    driver.close()