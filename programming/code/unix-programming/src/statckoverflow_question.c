/*
 * statckoverflow_question.c
 *
 *  Created on: Jun 13, 2015
 *      Author: root
 */

#define HELLO "hello"

static const char HELLO2[] = "hello";

static const char *HELLO3 = "hello";

static const char HELLO_WORLD[] = HELLO ", world";

void test_constant_string()
{
	sizeof(HELLO2);
}
