#!/bin/sh

http $(oc get route/api-gateway -o jsonpath='{.spec.host}')/gateway/projects/$1