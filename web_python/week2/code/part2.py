import os

import bs4
from bs4 import BeautifulSoup
import re
import unittest


def parse(path_to_file):
    # Поместите ваш код здесь.
    # ВАЖНО!!!
    # При открытии файла, добавьте в функцию open необязательный параметр
    # encoding='utf-8', его отсутствие в коде будет вызвать падение вашего
    # решения на грейдере с ошибкой UnicodeDecodeError
    with open(path_to_file, encoding='utf-8') as f:
        html = f.read()
        soup = BeautifulSoup(html, 'lxml')
        body = soup.find(id='bodyContent')

        # img
        img_cnt = 0
        imgs = body.find_all('img')
        for img in imgs:
            if img.has_attr('width') and int(img['width']) >= 200:
                img_cnt += 1

        # headers
        letters = {'E', 'T', 'C'}
        header_cnt = 0
        headers = body.find_all(re.compile('^h[1-6]$'))
        for header in headers:
            if header.text[0] in letters:
                header_cnt += 1

        # links
        links = body.find_all('a')
        links_mx = 0
        for link in links:
            cur = 1
            while link.next_sibling and \
                    (type(link.next_sibling) is not bs4.element.Tag or link.next_sibling.name == 'a'):
                if link.next_sibling.name == 'a':
                    cur += 1
                link = link.next_sibling
            links_mx = max(links_mx, cur)

        # lists
        lists_cnt = 0
        lists = body.find_all(['ul', 'ol'])
        for list in lists:
            if not list.find_parents(['ul', 'ol']):
                lists_cnt += 1

    return [img_cnt, header_cnt, links_mx, lists_cnt]


def build_bridge(path, start_page, end_page):
    """возвращает список страниц, по которым можно перейти по ссылкам со start_page на
    end_page, начальная и конечная страницы включаются в результирующий список"""
    # напишите вашу реализацию логики по вычисления кратчайшего пути здесь

    queue = [start_page]
    visited = {start_page}
    dist = {start_page: [start_page]}
    query = re.compile(r"(?<=/wiki/)[\w()]+")
    while len(queue) > 0:
        page = queue.pop(0)
        p = os.path.join(path, page)
        if os.path.isfile(p):
            with open(p, encoding="utf-8") as file:
                links = re.findall(query, file.read())
                for link in links:
                    p = os.path.join(path, link)
                    if os.path.isfile(p) and link not in visited:
                        queue.append(link)
                        visited.add(link)
                        dist[link] = dist[page] + [link]

    return dist[end_page]


def get_statistics(path, start_page, end_page):
    """собирает статистику со страниц, возвращает словарь, где ключ - название страницы,
    значение - список со статистикой страницы"""

    # получаем список страниц, с которых необходимо собрать статистику
    pages = build_bridge(path, start_page, end_page)
    # напишите вашу реализацию логики по сбору статистики здесь
    statistic = {}
    for page in pages:
        result = parse(os.path.join(path, page))
        statistic[page] = result
    return statistic


# from pprint import pprint
# result = get_statistics('wiki/', 'The_New_York_Times', "Binyamina_train_station_suicide_bombing")
# pprint(result)
# {'Binyamina_train_station_suicide_bombing': [1, 3, 6, 21],
#  'Haifa_bus_16_suicide_bombing': [1, 4, 15, 23],
#  'Second_Intifada': [9, 13, 14, 84],
#  'The_New_York_Times': [5, 9, 8, 42]}


# Набор тестов для проверки студентами решений по заданию "Практическое задание
# по Beautiful Soup - 2". По умолчанию файл с решением называется solution.py,
# измените в импорте название модуля solution, если файл с решением имеет другое имя.
STATISTICS = {
    'Artificial_intelligence': [8, 19, 13, 198],
    'Binyamina_train_station_suicide_bombing': [1, 3, 6, 21],
    'Brain': [19, 5, 25, 11],
    'Haifa_bus_16_suicide_bombing': [1, 4, 15, 23],
    'Hidamari_no_Ki': [1, 5, 5, 35],
    'IBM': [13, 3, 21, 33],
    'Iron_Age': [4, 8, 15, 22],
    'London': [53, 16, 31, 125],
    'Mei_Kurokawa': [1, 1, 2, 7],
    'PlayStation_3': [13, 5, 14, 148],
    'Python_(programming_language)': [2, 5, 17, 41],
    'Second_Intifada': [9, 13, 14, 84],
    'Stone_Age': [13, 10, 12, 40],
    'The_New_York_Times': [5, 9, 8, 42],
    'Wild_Arms_(video_game)': [3, 3, 10, 27],
    'Woolwich': [15, 9, 19, 38]}

TESTCASES = (
    ('wiki/', 'Stone_Age', 'Python_(programming_language)',
     ['Stone_Age', 'Brain', 'Artificial_intelligence', 'Python_(programming_language)']),

    ('wiki/', 'The_New_York_Times', 'Stone_Age',
     ['The_New_York_Times', 'London', 'Woolwich', 'Iron_Age', 'Stone_Age']),

    ('wiki/', 'Artificial_intelligence', 'Mei_Kurokawa',
     ['Artificial_intelligence', 'IBM', 'PlayStation_3', 'Wild_Arms_(video_game)',
      'Hidamari_no_Ki', 'Mei_Kurokawa']),

    ('wiki/', 'The_New_York_Times', "Binyamina_train_station_suicide_bombing",
     ['The_New_York_Times', 'Second_Intifada', 'Haifa_bus_16_suicide_bombing',
      'Binyamina_train_station_suicide_bombing']),

    ('wiki/', 'Stone_Age', 'Stone_Age',
     ['Stone_Age', ]),
)


class TestBuildBrige(unittest.TestCase):
    def test_build_bridge(self):
        for path, start_page, end_page, expected in TESTCASES:
            with self.subTest(path=path,
                              start_page=start_page,
                              end_page=end_page,
                              expected=expected):
                result = build_bridge(path, start_page, end_page)
                self.assertEqual(result, expected)


class TestGetStatistics(unittest.TestCase):
    def test_build_bridge(self):
        for path, start_page, end_page, expected in TESTCASES:
            with self.subTest(path=path,
                              start_page=start_page,
                              end_page=end_page,
                              expected=expected):
                result = get_statistics(path, start_page, end_page)
                self.assertEqual(result, {page: STATISTICS[page] for page in expected})


if __name__ == '__main__':
    unittest.main()
