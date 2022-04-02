import smtplib
from email.message import EmailMessage

def send_email(msg):
    mail = EmailMessage()
    mail.set_content(f"{msg}")
    mail["Subject"] = "cryptoGecko by @federicocabreraf"
    mail["From"] = "#"
    mail["To"] = "#"
    server = smtplib.SMTP("smtp.gmail.com", 587)
    server.starttls()
    server.login("#@gecko.com", "#")
    server.send_message(mail)
    server.quit()
