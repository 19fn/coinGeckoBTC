FROM python:3.9-slim-bullseye

WORKDIR /opt/coin-gecko/

COPY . .

RUN python3 -m venv /coin-gecko/venv

ENV PATH="/coin-gecko/venv/bin:$PATH"

RUN pip install -r requirements.txt

CMD ["./run.sh"]
