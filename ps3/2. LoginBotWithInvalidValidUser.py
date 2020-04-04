import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys


class Search(unittest.TestCase):
    def setUp(self):
        options = webdriver.ChromeOptions()
        options.binary_location = "//Volumes/Apps/Applications/Chrome.app/Contents/MacOS/Google Chrome"
        # options.headless = True
        chrome_driver_binary = "/usr/local/bin/chromedriver"
        self.driver = webdriver.Chrome(chrome_driver_binary,
                                       chrome_options=options)

    def test_invalid_1(self):
        driver = self.driver
        driver.get("https://statcounter.com/login/")
        username = driver.find_element_by_id("username")
        password = driver.find_element_by_id("password")

        username.send_keys("username1")
        password.send_keys("hunter123")

        driver.find_element_by_class_name("submit").click()
        assert "No results found." not in driver.page_source

    def test_invalid_2(self):
        driver = self.driver
        driver.get("https://statcounter.com/login/")
        username = driver.find_element_by_id("username")
        password = driver.find_element_by_id("password")

        username.send_keys("johnbob")
        password.send_keys("12345678")

        driver.find_element_by_class_name("submit").click()
        assert "No results found." not in driver.page_source

    def test_invalid_3(self):
        driver = self.driver
        driver.get("https://statcounter.com/login/")
        username = driver.find_element_by_id("username")
        password = driver.find_element_by_id("password")

        username.send_keys("appleseed")
        password.send_keys("TimApple!")

        driver.find_element_by_class_name("submit").click()
        assert "No results found." not in driver.page_source

    def test_valid(self):
        driver = self.driver
        driver.get("https://statcounter.com/login/")
        username = driver.find_element_by_id("username")
        password = driver.find_element_by_id("password")

        username.send_keys("account1029")
        password.send_keys("Dispostable12")

        driver.find_element_by_class_name("submit").click()
        assert "menu-log-out" in driver.page_source

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()