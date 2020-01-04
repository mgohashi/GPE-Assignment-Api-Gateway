#!/bin/sh

http $(oc get route/api-gateway -o jsonpath='{.spec.host}')/gateway/freelancers/$1