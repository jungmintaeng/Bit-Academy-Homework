import os

# configuration
CONFIG = {
    'district': '서울특별시',
    'countries': [('중국', 112), ('일본', 130), ('미국', 275)],
    'common': {
        'service_key': 'wYb0rhU3ShdiwOJ3IC6A6CNWenlUKcmE70qtEVtFBDu73aTUEQUUWqWEpMiT79PS0BozuoGDOqYCiNKS31BzWw%3D%3D',
        'start_year': 2011,
        'end_year': 2017,
        'restore_directory': '__results__/crawling',
        'fetch': False}}


if not os.path.exists(CONFIG['common']['restore_directory']):
    os.makedirs(CONFIG['common']['restore_directory'])
