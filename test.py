import requests

url = 'http://127.0.0.1:8080/third/warehouse/list'

response = requests.get(url)  # 使用json参数发送数据
print(response.text)