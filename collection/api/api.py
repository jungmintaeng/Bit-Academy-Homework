from analysis_pd.collection.api.json_request import json_request
from analysis_pd.config import CONFIG
from urllib.parse import quote
from itertools import count
import json


def pd_gen_url(endpoint, service_key=None, **params):
    url = endpoint
    url = url + '?_type=json&serviceKey=' + CONFIG['common']['service_key'] if service_key is None else service_key
    for k, v in params.items():
        url += '&{0}={1}'.format(k, v)
    return url


def pd_fetch_tourspot_visitor(
        district1='',
        district2='',
        tourspot='',
        year=0,
        month=0,
        service_key=''):
    # http://openapi.tour.go.kr/openapi/service/TourismResourceStatsService/getPchrgTrrsrtVisitorList?YM=201201&SIDO=%EB%B6%80%EC%82%B0%EA%B4%91%EC%97%AD%EC%8B%9C&_type=json&serviceKey=%2FfZdR%2Bue1CSxLEnMkZXa9iDYontLTMTIteD5%2BzYCiMYpDKUZNUh2FHGDQ04zazSEmLl34FClDQk8a7flFCIQKA%3D%3D
    result = []
    endpoint = 'http://openapi.tour.go.kr/openapi/service/TourismResourceStatsService/getPchrgTrrsrtVisitorList'
    for i in count(start=1):
        url = pd_gen_url(endpoint, service_key, SIDO=quote(district1), YM=str(year) + leading_zero(month), GUNGU=district2,
                         RES_NM=tourspot,
                         pageNo=i)
        json_result = json_request(url, 'utf-8')
        try:
            total_count = json_result.get('response').get('body').get('totalCount')
            data = json_result.get('response').get('body').get('items').get('item')
            result += data
        except AttributeError:
            return []
        if total_count <= 10 * i:
            break
    return result


def pd_fetch_foreign_visitor(country_code=0, year=0, month=0, service_key=''):
    endpoint = 'http://openapi.tour.go.kr/openapi/service/EdrcntTourismStatsService/getEdrcntTourismStatsList'
    url = pd_gen_url(endpoint, service_key, ED_CD='E', NAT_CD=country_code, YM=str(year) + leading_zero(month))
    json_result = json_request(url, 'utf-8')
    try:
        return json_result.get('response').get('body').get('items').get('item')
    except AttributeError:
        return {}


def leading_zero(month):
    return str(month).rjust(2, '0')


if __name__ == '__main__':
    pd_fetch_tourspot_visitor(district1='서울특별시', year=2017, month=1)
