import requests
import json

api_str = 'https://fantasy.premierleague.com/drf/entry/'

players = []

with open('players10k.json') as f:
    players = json.load(f)

costs = []
for index, player in enumerate(players[0:1000]):
    r = requests.get(api_str + str(player['entry']) + '/history')
    print('Player {}'.format(index + 1), end='')
    if r.status_code == 200:
        print(' Ok ', end='')
        cost = 0
        player_data = r.json()
        for gw in player_data['history']:
            cost += gw['event_transfers_cost']
        print(cost)
        costs.append(cost)

with open('hits.json', 'w') as f:
    json.dump(costs, f)
