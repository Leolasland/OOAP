1. Класс Книга "содержит" русский язык. Возможное отношение - эта книга "является" Книга_на_русском_языке. Но тогда,
при переводе книги на другой язык нужно создавать свой класс на каждый язык, поэтому лучше чтобы Книга содержала поле
со множеством языков.
2. Класс Мороженое "содержит" фруктовый сок. Возможное отношение "является" Фруктовым мороженым. Но мороженое может не 
содержать сливок и молока, а только фруктовый сок и быть фруктовым льдом. Лучше если это будет отдельной характеристикой 
добавки. И в добавках может быть как фруктовый сок, так и орешки, шоколад, сироп, без необходимости создания отдельных классов 
на каждое такое мороженое.