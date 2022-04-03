FROM python:3.9-slim-bullseye

WORKDIR /opt/coin-gecko/

ENV VIRTUAL_ENV=/opt/coin-gecko/venv

COPY . .

RUN python3 -m venv $VIRTUAL_ENV

ENV PATH="${VIRTUAL_ENV}/bin:$PATH"

RUN pip install -r requirements.txt && mkdir logs && cd logs/

CMD ["/opt/coin-gecko/run.sh"]
