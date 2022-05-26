#!/usr/bin/python3
import requests, lxml, argparse, signal
from datetime import datetime
from fly_email import send_email
from bs4 import BeautifulSoup as bs4

# CHANGE THIS
HEADER = {"User-Agent" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:100.0) Gecko/20100101 Firefox/100.0"}
SOURCE = "https://pharos.sh/guia-para-analizar-html-con-beautifulsoup-en-python/"

def ctrl_c(sig, frame):
    print("\n[!] Se presionó (ctrl_c) saliendo...")
    exit()
signal.signal(signal.SIGINT, ctrl_c)

def coinName(coin):
    coin = coin.lower()
    if coin == "btc" or coin == "bitcoin":
        return "btc"
    elif coin == "eth" or coin == "ethereum":
        return "eth"
    elif coin == "tether" or coin == "usdt":
        return "usdt"
    elif coin == "binance coin" or coin == "bnb":
        return "bnb"
    elif coin == "solana" or coin == "sol":
        return "sol"
    else:
        return print("No coin")

def coinID(coin):
    if coin == "btc":
        return 1
    elif coin == "eth":
        return 279
    elif coin == "usdt":
        return 325
    elif coin == "bnb":
        return 825
    elif coin == "sol":
        return 4128
    else:
        return 0

def getCripto(coin_name):
    #try:
        url = SOURCE
        page = requests.get(url,HEADER)
        soup = bs4(page.content, "lxml")
        coin = coinName(coin_name)
        data_coin_id = "BTC"
        data_list = []
        #soup.select(span[data-coin-symbol='btc'])
        #return print(soup.select(f"span[data-coin-id='{data_coin_id}']"))
        #data_list.append(coin.upper())
        print(soup.find_all("a"))
        # for item in soup.select(f"td[title=BTC]"):
        #     data_list.append(item)
        # #    print(item)
        # return print(data_list)
        #coin_price = data_list[1][1:]
        #coin_format = coin_price.replace(",","")
        #coin_val = ""
        #if coin_format <= f'{coin_value}':
        #    coin_val = data_list[1]
        #fecha = datetime.now()
        #fecha = fecha.strftime('%d/%m/%Y  %H:%M:%S')
        #if coin_val:
        #    msg = "[!]",coin.upper(),"value is less equal your estimated:",coin_val
            #send_email(msg)

            #return print("\n[*] Coin:",coin.upper(),"\n[+]",fecha,"\n[+] C-P-1h-24h-7d:",data_list,"\n[+] Value is less equal your estimated:",coin_val,"\n\n")
        #else:
        #return print("\n[*] Coin:",coin.upper(),"\n[+]",fecha,"\n[+] C-P-1h-24h-7d:",data_list,"\n\n----------------------------------------------------------------------")
    #except:
    #    print("\n\n[!] Ha occurido un error.\n\n")


if __name__ == "__main__":
    ### CLI ###
    #parser = argparse.ArgumentParser(description="[*] CryptoGecko Script by @federicocabreraf.")
    #parser.add_argument('-c', '--coin', help='print cripto data.', required=True, action='append')
    #parser.add_argument('-v', '--value', help='notify when value is less equal.', required=False)
    #coin = parser.parse_args()
    #for i in range(len(coin.coin)):
    #    getCripto(coin.coin[i])  
   getCripto("btc")
