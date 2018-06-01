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
        # json_result = json.loads('{"response":{"header":{"resultCode":"0000","resultMsg":"OK"},"body":{"items":{"item":[{"addrCd":2635,"csForCnt":286,"csNatCnt":9110,"gungu":"해운대구","resNm":"부산시립미술관","rnum":1,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":80,"csNatCnt":937,"gungu":"해운대구","resNm":"M.Do 관광호텔","rnum":2,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":7004,"csNatCnt":16473,"gungu":"해운대구","resNm":"파라다이스호텔부산","rnum":3,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":59,"csNatCnt":1436,"gungu":"해운대구","resNm":"호텔일루아","rnum":4,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":2063,"csNatCnt":4830,"gungu":"해운대구","resNm":"해운대그랜드호텔","rnum":5,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":1403,"csNatCnt":6012,"gungu":"해운대구","resNm":"부산웨스턴조선호텔","rnum":6,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":712,"csNatCnt":17025,"gungu":"해운대구","resNm":"해운대 글로리콘도","rnum":7,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":1113,"csNatCnt":5736,"gungu":"해운대구","resNm":"노보텔부산","rnum":8,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":9610,"csNatCnt":37223,"gungu":"해운대구","resNm":"해운대 한화리조트 부산","rnum":9,"sido":"부산광역시","ym":201201},{"addrCd":2635,"csForCnt":73,"csNatCnt":1188,"gungu":"해운대구","resNm":"송정관광호텔","rnum":10,"sido":"부산광역시","ym":201201}]},"numOfRows":10,"pageNo":1,"totalCount":10}}}')
        try:
            total_count = json_result.get('response').get('body').get('totalCount')
            data = json_result.get('response').get('body').get('items').get('item')
            for item in data:
                parsed_data = dict()
                parsed_data['count_locals'] = item.get('csNatCnt', '')
                parsed_data['count_foreigner'] = item.get('csForCnt', '')
                parsed_data['tourist_spot'] = item.get('resNm', '')
                parsed_data['date'] = item.get('ym', '')
                parsed_data['restrict1'] = item.get('sido', '')
                parsed_data['restrict2'] = item.get('gungu', '')
                result.append(parsed_data)
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
