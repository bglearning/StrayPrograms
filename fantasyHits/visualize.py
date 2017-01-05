import matplotlib.pyplot as plt
import json

costs = []
with open('hits.json') as f:
    costs = json.load(f)

players = list(range(0, 1000))
hits = [cost // 4 for cost in costs]

hit_freq = [0] * 30

for hit in hits:
    hit_freq[hit] += 1

plt.scatter(list(range(0, 30)), hit_freq, c='b', s=50)
plt.title('Hit pattern of players in the top 1K')

plt.xlabel('Number of hits')
plt.ylabel('Number of players')

plt.show()
