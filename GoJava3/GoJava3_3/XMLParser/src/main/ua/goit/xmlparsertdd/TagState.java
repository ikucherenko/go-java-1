package ua.goit.xmlparsertdd;

enum TagState {
  INIT {

    @Override
    public TagState next(char c, TagBuilder builder) {
      TagState result = INVALID_END;
      result = TagStateMachine.handleOpenBracket(c, result);
      if (result == OPEN) {
        builder.setType(TagType.OPEN);
      }
      return result;
    }
  },
  OPEN {

    @Override
    public TagState next(char c, TagBuilder builder) {
      TagState result = INVALID_END;
      result = TagStateMachine.handleFirstLetterForName(c, result);
      result = TagStateMachine.handleSpace(c, result);
      return result;
    }
  },
  NAME_FOR_TAG {

    @Override
    public TagState next(char c, TagBuilder builder) {
      TagState result = INVALID_END;
      result = TagStateMachine.handleCloseBracket(c, result);
      result = TagStateMachine.handleLetterForName(c, result);
      return  result;
    }
  },
  UNCHEKED_TAG_END {
    @Override
    public TagState next(char c, TagBuilder builder) {
      //TODO: make checking in stack
      return VALID_TAG_END;
    }
  },
  
  VALID_TAG_END {

    @Override
    public TagState next(char c, TagBuilder builder) {
      return VALID_TAG_END;
    }
  },
  INVALID_END {

    @Override
    public TagState next(char c, TagBuilder builder) {
      return INVALID_END;
    }
  };

  abstract TagState next(char c, TagBuilder builder);
}
