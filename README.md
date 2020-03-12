# My purpose -> Manage Your Time, Enjoy Your Life, To Be Yourself

## Relative Notes On Shimo web site https://shimo.im/docs/6K3wgcyGhTK9YKDj

## Problems during the development
### To solve the gateway timeout problem
The detail of ther problem is that when I try to update database, it will always timeout but query request works well.
Actually, I just make a mistake of spelling, set read timeout for ribbon works well. So, this problem has been resolved now.

### Hystrix and ribbon timeout setting
The Hystrix timeout of 60000ms for the command user is set lower than the combination of the Ribbon read and connect timeout, 122000ms.

### 