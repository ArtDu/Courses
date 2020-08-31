# from pprint import pprint

from bs4 import BeautifulSoup
from decimal import Decimal
# import requests


def convert(amount, cur_from, cur_to, date, requests):
    response = requests.get(
        'https://www.cbr.ru/scripts/XML_daily.asp',
        params={
            "date_req": date
        }
    )  # Использовать переданный requests

    soup = BeautifulSoup(response.content, "xml")
    rur = BeautifulSoup('<Valute ID="1"> \
                                <CharCode>RUR</CharCode> \
                                <Nominal>1</Nominal> \
                                <Value>1</Value> \
                             </Valute>', "xml")
    soup.append(rur)

    from_ = Decimal(soup.find('CharCode', text=cur_from).find_next_sibling('Value').string.replace(',', '.')) / \
            Decimal(soup.find('CharCode', text=cur_from).find_next_sibling('Nominal').string.replace(',', '.'))
    to_ = Decimal(soup.find('CharCode', text=cur_to).find_next_sibling('Value').string.replace(',', '.')) / \
          Decimal(soup.find('CharCode', text=cur_to).find_next_sibling('Nominal').string.replace(',', '.'))
    result = amount * from_ / to_

    return result.quantize(Decimal('1.0000'))  # не забыть про округление до 4х знаков после запятой


# print(convert(Decimal("1000.1000"), 'USD', 'JPY', "17/02/2005", requests))
