alter table rants add constraint questions_fkey foreign key (question_id) references questions (id) match full;