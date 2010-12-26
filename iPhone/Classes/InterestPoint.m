#import "InterestPoint.h"

@implementation InterestPoint

@synthesize id = _id;
@synthesize location = _location;
@synthesize category = _category;
@synthesize title = _title;
@synthesize summary = _summary;
@synthesize color = _color;
@synthesize categoryID = _categoryID;

-(id) initWithHmm: (NSInteger) id {
    
	self = [super init];
	if (self != nil) {
        
	}
	return self;
}

- (void) dealloc {
    
    [_category release];
    [_title release];
    [_summary release];
    [_color release];
	[super dealloc];
}

@end
