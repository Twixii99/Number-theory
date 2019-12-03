#include <bits/stdc++.h>
using namespace std;

int geteuclidean (int a, int b, int *x, int *y)
{
    // Base Case
    if (a == 0)
    {
        *x = 0;
        *y = 1;
        return b;
    }
    int x1, y1; // To store results of recursive call
    int gcd = geteuclidean (b%a, a, &x1, &y1);

    // Update x and y using results of
    // recursive call
    *x = y1 - (b/a) * x1;
    *y = x1;
    return gcd;
}

int main()
{
    int x, y, a , b ; cout<<"enter a , b : ";
    cin >> a >> b;
    int g = geteuclidean(a, b, &x, &y);
    cout << "GCD(" << a << ", " << b
         << ") = " << g << endl;
    cout << g << " = " << x << "*" << a << " + " << y << "*" << b << endl;
  
  return 0;
}