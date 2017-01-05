import requests
import json

api_str = 'https://fantasy.premierleague.com/drf/leagues-classic-standings/313?\
        phase=1&le-page=1&ls-page='

page_number = 1
per_page = 50
total_pages = 10000 // per_page

players = []

for page_number in range(1, total_pages + 1):
    r = requests.get(api_str + str(page_number))
    print('Page: {}'.format(page_number), end='')
    if r.status_code == 200:
        print(' Ok')
        data = r.json()
        players += data['standings']['results'][0:per_page]

print(len(players))

with open('players10k.json', 'w') as f:
    json.dump(players, f)
