#!/bin/sh

export PORT=3001; export NODE_OPTIONS=--openssl-legacy-provider; yarn install && yarn start

