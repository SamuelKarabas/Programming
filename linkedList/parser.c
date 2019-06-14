#include <stdlib.h>

#include "parser.h"
#include "command.h"
#include "container.h"

struct parser* create_parser() {
    struct parser* novy_parser = calloc((size_t) 1, sizeof(struct parser));
    if(novy_parser == NULL) {
        return NULL;
    }

    novy_parser->commands = create_container(novy_parser->commands, COMMAND, create_command("ULOZ", "Príkaz uloží stav rozohratej hry na disk. Voliteľným parametrom je cesta k súboru.", "^(ULOZ|SAVE) *(.*)$", 2));

    return novy_parser;
}

struct parser* destroy_parser(struct parser* parser) {
    if(parser == NULL) {
        return NULL;
    }

    destroy_containers(parser->history);
    destroy_containers(parser->commands);
    free(parser);

    return NULL;
}

struct command* parse_input(struct parser* parser, char* input) {
    return NULL;
}
