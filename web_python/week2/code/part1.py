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


class TestParse(unittest.TestCase):
    def test_parse(self):
        test_cases = (
            ('wiki/Stone_Age', [13, 10, 12, 40]),
            ('wiki/Brain', [19, 5, 25, 11]),
            ('wiki/Artificial_intelligence', [8, 19, 13, 198]),
            ('wiki/Python_(programming_language)', [2, 5, 17, 41]),
            ('wiki/Spectrogram', [1, 2, 4, 7]),)

        for path, expected in test_cases:
            with self.subTest(path=path, expected=expected):
                self.assertEqual(parse(path), expected)


if __name__ == '__main__':
    unittest.main()