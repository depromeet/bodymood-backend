DROP INDEX uni_member_1;

CREATE INDEX "idx_member_2"
    ON member ("social_id", "social_provider");
