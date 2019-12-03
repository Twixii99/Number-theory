#include <bits/stdc++.h>

using namespace std;

long long int  fastexponent1( long a,  long b , int m) {
  long c = 1;
  for(int i = 1; i <= b; i++)
    c = c * a;
  return c % m;
}

long long int  fastexponent2(long a, long b , int m) {
  long c = 1;
  for(int i = 1; i <= b; i++)
  c = (c * a) % m;
  return c % m ;
}

long long int  fastexponent3(long a, long b, int m) {
    if (a == 0)
        return 0;
    if (b == 0)
        return 1;
    long c;
    if (b % 2 == 0) {
        c = fastexponent3(a, b / 2, m);
        c = (c * c) % m;
    }
    else {
        c = a % m;
        c = (c * fastexponent3(a, b - 1, m) % m) % m;
    }

    return (c + m) % m;
}

long long int fastexponent4(long a,  long b, int m) {
    long  c = 1;

    while (b > 0)
    {
        if (b % 2 == 1)
            c = (c * a) % m;
        b = b / 2;
        a = (a * a) % m;
    }
    return c;
}

int main()  
{  
  long a , b; int  m;  
  while(1) {
    cout << "enter a, b, m : ";
    cin >> a >> b >> m;
    printf("Power is %lld\n", fastexponent3(a, b, m));
  }
  return 0;
}