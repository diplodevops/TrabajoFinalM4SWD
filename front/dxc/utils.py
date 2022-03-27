import requests

BASE_URL = 'http://back:8080/rest/msdxc/dxc?sueldo={sueldo}&ahorro={ahorro}'

def get_dxc(sueldo, ahorro):
    url = BASE_URL.format(sueldo=sueldo, ahorro=ahorro)

    response = requests.get(url).json()

    return response


if __name__ == '__main__':
    response = get_dxc()
    print(response)
