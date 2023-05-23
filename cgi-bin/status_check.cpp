#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
using namespace std;

int main () {
   int status;
   char t[1024]="ps -eo lstart,cmd | grep ";
   cout << "Content-type:text/html\r\n\r\n"<<endl;
   char *value = getenv("QUERY_STRING");
   strcat(t,value);
   status = system(strcat(t," | grep -v grep | head -n 1 | awk '{ print $1\" \"$3\" \"$2\" \"$5\" \"$4}'"));
   
   return 0;
}
