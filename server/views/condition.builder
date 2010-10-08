xml.instruct!
xml.conditions do
  @conditions.each do |cond|
    xml.condition do
      xml.condition cond[:id]
      xml.desc cond[:desc]
    end
  end
end
