import requests

url = 'http://127.0.0.1:8080/user/addr/get'

response = requests.get(url)
print(response.text)