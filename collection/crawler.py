from analysis_pd.config import CONFIG
from analysis_pd.collection.api.api import *
import json
from datetime import datetime


def preprocess_tourspot_visitor(data):
    result = []
    for item in data:
        parsed_data = dict()
        parsed_data['count_locals'] = item.get('csNatCnt', '')
        parsed_data['count_foreigner'] = item.get('csForCnt', '')
        parsed_data['tourist_spot'] = item.get('resNm', '')
        parsed_data['date'] = item.get('ym', '')
        parsed_data['restrict1'] = item.get('sido', '')
        parsed_data['restrict2'] = item.get('gungu', '')
        result.append(parsed_data)
    return result


def preprocess_foreign_visitor(data):
    parsed_data = dict()
    parsed_data['country_code'] = data.get('natCd', '')
    parsed_data['country_name'] = data.get('natKorNm', '')
    parsed_data['date'] = data.get('ym', '')
    parsed_data['visit_count'] = data.get('num', '')
    return parsed_data


def crawling_tourspot_visitor(
        district,
        start_year,
        end_year,
        restore_directory=None,
        service_key=None,
        fetch=True):
    result = []
    file_name = '{0}/{1}_tourinstspot_{2}_{3}.json'.format(
        CONFIG['common']['restore_directory'] if restore_directory is None else restore_directory, district, start_year,
        end_year)
    # 서울특별시_tourinstspot_2017_2017.json
    now = datetime.now()
    nowtp = now.timetuple()
    now_year = nowtp.tm_year
    now_month = nowtp.tm_mon
    for year in range(start_year, end_year + 1, 1 if start_year <= end_year else -1):
        if year > now_year:
            continue
        for month in range(1, 13):
            if year == now_year and month > now_month:
                continue
            data = pd_fetch_tourspot_visitor(district1=district, year=year, month=month, service_key=service_key)
            result += preprocess_tourspot_visitor(data)
    with open(file_name, 'w', encoding='utf-8') as outfile:
        json_string = json.dumps(result, indent=4, sort_keys=True, ensure_ascii=False)
        outfile.write(json_string)
    return file_name


def crawling_foreign_visitor(
        country,
        start_year,
        end_year,
        restore_directory=None,
        service_key=None,
        fetch=True):
    result = []
    file_name = '{0}/{1}({2})_foreignvisitor_{3}_{4}.json'.format(
        CONFIG['common']['restore_directory'] if restore_directory is None else restore_directory, country[0],
        country[1], start_year,
        end_year)
    # 중국(112)_foreignvisitor_2017_2017.json

    now = datetime.now()
    nowtp = now.timetuple()
    now_year = nowtp.tm_year
    now_month = nowtp.tm_mon

    for year in range(start_year, end_year + 1, 1 if start_year <= end_year else -1):
        if year > now_year:
            continue
        for month in range(1, 13):
            if year == now_year and month > now_month:
                continue
            result.append(
                preprocess_foreign_visitor(pd_fetch_foreign_visitor(country[1], year, month, service_key=service_key))
            )
    with open(file_name, 'w', encoding='utf-8') as outfile:
        json_string = json.dumps(result, indent=4, sort_keys=True, ensure_ascii=False)
        outfile.write(json_string)
    return file_name


if __name__ == '__main__':
    crawling_foreign_visitor(('중국', 112), 2017, 2017)
