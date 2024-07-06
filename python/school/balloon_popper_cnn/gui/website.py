from selenium import webdriver
import pyautogui as robot
import numpy as np

robot.PAUSE = 0
robot.FAILSAFE = False


def open_window():
    global driver
    print(robot.size())

    driver = webdriver.Firefox(webdriver.firefox.options.Options())
    driver.set_window_size(720, 900)
    driver.set_window_position(0, 0)
    driver.get("https://www.google.com/search?q=bts")

    button_position = np.array([70, 280])
    button_position = button_position / np.array(
        [1440, 900]
    )  # ! scaling w/ magic numbers
    button_position = button_position * np.array([1920, 1200])
    robot.moveTo(x=button_position[0], y=button_position[1])
    robot.click()


def close_window():
    global driver

    driver.close()
