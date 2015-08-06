/*
 * cover.h
 *
 *  Created on: 2015-6-28
 *      Author: user
 */

#ifndef COVER_H_
#define COVER_H_

#include "set.h"

typedef struct KSet_
{
    void *key;
    Set set;
} KSet;

int cover(Set *member, Set *subsets, Set *covering);


#endif /* COVER_H_ */
