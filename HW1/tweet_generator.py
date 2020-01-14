import random
import string

template = 'feijwgnrewignrf ewqoifn ewqof qowfn ewioqn dwodm sa,vsdvofdng rifneqwe1r1 re1o rne1r eoen fkwovn bovfowvnfow fvweov efwio vefinionw e oeo qq'
with open('tweets.txt', 'w') as f:
    for i in range(10**6):
        start_end = sorted([random.randrange(0, 140), random.randrange(0, 140)])
        while start_end[1] - start_end[0] < 10:
            start_end = sorted([random.randrange(0, 140), random.randrange(0, 140)])
        f.write(f"{template[start_end[0]:start_end[1]].strip()}\n")
